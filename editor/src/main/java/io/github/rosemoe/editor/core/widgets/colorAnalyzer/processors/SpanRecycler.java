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
package io.github.rosemoe.editor.core.widgets.colorAnalyzer.processors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanLineController;
import io.github.rosemoe.editor.core.widgets.colorAnalyzer.controller.spans.SpanMapController;
import io.github.rosemoe.editor.core.util.Logger;

/**
 * Remove object on an other thread to prevent ui freeze.
 */
public class SpanRecycler {

    private static SpanRecycler INSTANCE;
    private final BlockingQueue<SpanMapController> taskQueue;
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

    public void recycle(SpanMapController spans) {
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

        RecycleThread() {
            setDaemon(true);
            setName("SpanRecycleDaemon");
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    try {
                        SpanMapController spanMap = taskQueue.take();
                        int count = 0;
                        for (SpanLineController line : spanMap.getLines()) {
                            int size = line.size();
                            for (int i = 0; i < size; i++) {
                                SpanController span = line.remove(size - 1 - i);
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
                Logger.debug(e.toString());
            }
            Logger.debug("SpanRecycler exited");
        }

    }

}
