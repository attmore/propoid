package propoid.test.db.service;

import propoid.db.Repository;
import propoid.db.service.RepositoryConnection;
import propoid.db.service.RepositoryService;
import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Test for {@link RepositoryService}.
 */
public class RepositoryServiceTest extends ApplicationTestCase<Application> {

	public RepositoryServiceTest() {
		super(Application.class);
	}

	public void testConnection() throws Exception {

		final boolean[] connected = new boolean[1];

		final RepositoryConnection connection = new RepositoryConnection() {
			@Override
			public void onConnected(Repository repository) {
				connected[0] = true;
				synchronized (connected) {
					connected.notify();
				}
			}

			@Override
			public void onDisconnected() {
			}
		};

		try {
			connection.bind(getContext(), RepositoryService.class);

			fail();
		} catch (IllegalArgumentException expected) {
		}

		connection.bind(getContext(), TestRepositoryService.class);

		synchronized (connected) {
			connected.wait(5000);
		}

		assertEquals(true, connected[0]);

		connection.unbind();
	}
}