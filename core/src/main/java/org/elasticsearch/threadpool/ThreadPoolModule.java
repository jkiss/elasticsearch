/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.threadpool;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.settings.SettingsModule;

public class ThreadPoolModule extends AbstractModule {

    private final ThreadPool threadPool;

    public ThreadPoolModule(final ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void prepareSettings(SettingsModule settingsModule) {
        for (final ExecutorBuilder<?> builder : threadPool.builders()) {
            builder.getRegisteredSettings().forEach(settingsModule::registerSetting);
        }
    }

    @Override
    protected void configure() {
        bind(ThreadPool.class).toInstance(threadPool);
    }

}
