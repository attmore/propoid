/*
 * Copyright 2011 Sven Meier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package propoid.db.locator;

import propoid.db.Locator;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 */
public class ContextBasedLocator implements Locator {

	private Context context;
	private String name;

	private SQLiteDatabase database;

	public ContextBasedLocator(Context context, String name) {
		this.context = context;
		this.name = name;
	}

	public SQLiteDatabase open() {
		if (database != null) {
			throw new IllegalStateException("already open");
		}

		database = context.openOrCreateDatabase(name,
				Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE,
				null);
		return database;
	}

	@Override
	public void close() {
		database.close();
		database = null;
	}
}