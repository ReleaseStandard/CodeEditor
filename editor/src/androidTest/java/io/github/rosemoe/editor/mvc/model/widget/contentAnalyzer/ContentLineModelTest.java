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
package io.github.rosemoe.editor.mvc.model.widget.contentAnalyzer;

import junit.framework.TestCase;

import io.github.rosemoe.editor.core.util.Logger;
import io.github.rosemoe.editor.core.util.Random;
import io.github.rosemoe.editor.core.widgets.contentAnalyzer.model.ContentLineModel;

public class ContentLineModelTest extends TestCase {
    final static Random r = new Random();

    protected void setUp() throws Exception {
        Logger.DEBUG = true;
    }

    public void testInitialise() {
    }

    public void testCheckIndex() {
    }

    public void testEnsureCapacity() {
        ContentLineModel clm = new ContentLineModel();
        clm.initialise(true);
        clm.ensureCapacity(10);
    }

    public void testInsert() {
        ContentLineModel clm = new ContentLineModel();
        clm.initialise(true);
        int max = 20+r.nextUint()%10;

        Logger.debug("max=",max);
        for(int a = 0 ; a <  max; a = a + 1 ) {
            int old = clm.length;
            char c = r.nextChar();
            Logger.debug(c);
            clm.insert(clm.length, c);
            assertTrue(clm.length == old+1);
        }
    }

    public void testTestInsert() {
    }

    public void testAppend() {
        ContentLineModel clm = new ContentLineModel();
        clm.initialise(true);
        clm.append("azeazeazezaeza",0,0);
        try {
            clm.append("azeazeazezaeza", 10, 0);
            assertTrue(false);
        } catch(Exception e) {
            assertTrue(true);
        }
        clm.append("azeazeazezaeza",0,0);
    }

    public void testDelete() {
        ContentLineModel clm = new ContentLineModel();
        clm.initialise(true);
        clm.delete(0,0);
    }

    public void testLastIndexOf() {
        String src = "okgoogle";
        String targ = "oo";
        ContentLineModel.lastIndexOf(src.toCharArray(),src.length(),targ.toCharArray(),targ.length(),0);
    }
}