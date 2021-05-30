/*
 *   Copyright 2020-2021 Rosemoe
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package io.github.rosemoe.editor.text.spanmap;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.github.rosemoe.editor.struct.Span;
import io.github.rosemoe.editor.util.Logger;

public class SpanRecycler {

    private static SpanRecycler INSTANCE;
    private final BlockingQueue<SpanMap> taskQueue;
    private Thread recycleThread;
    private SpanRecycler() {
        taskQueue = new ArrayBlockingQueue<>(8);
    }

    public static synchronized SpanRecycler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpanRecycler();
        }
        return INSTANCE;
    }

    public void recycle(SpanMap spans) {
        if (spans == null) {
            return;
        }
        if (recycleThread == null || !recycleThread.isAlive()) {
            recycleThread = new RecycleThread();
            recycleThread.start();
        }
        taskQueue.offer(spans);
    }

    private class RecycleThread extends Thread {

        private final static String LOG_TAG = "Span Recycle Thread";

        RecycleThread() {
            setDaemon(true);
            setName("SpanRecycleDaemon");
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    try {
                        SpanMap spanMap = taskQueue.take();
                        int count = 0;
                        for (SpanLine line : spanMap.getLines()) {
                            int size = line.size();
                            for (int i = 0; i < size; i++) {
                                Span span = line.remove(size - 1 - i);
                                if ( span == null ) { continue; }
                                span.recycle();
                                count++;
                            }
                        }
                        Logger.debug("Recycled " + count + " spans");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            } catch (Exception e) {
                Log.w(LOG_TAG, e);
            }
            Logger.debug("Recycler exited");
        }

    }

}
