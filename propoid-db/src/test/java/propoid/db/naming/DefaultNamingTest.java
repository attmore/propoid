package propoid.db.naming;

import junit.framework.TestCase;
import propoid.core.Propoid;
import propoid.db.Repository;

public class DefaultNamingTest extends TestCase {

	public void test() throws Exception {
		Repository repository = null;

		DefaultNaming naming = new DefaultNaming();

		assertEquals("Foo", naming.table(repository, Foo.class));
		assertEquals("Foo", naming.table(repository, FooExtended.class));

		assertEquals(null, naming.encodeType(repository, Foo.class));
		assertEquals("FooExtended",
				naming.encodeType(repository, FooExtended.class));

		assertEquals(Foo.class, naming.decodeType(repository, Foo.class, null));
		assertEquals(FooExtended.class,
				naming.decodeType(repository, Foo.class, "FooExtended"));
		assertEquals(FooExtended.class,
				naming.decodeType(repository, FooExtended.class, "FooExtended"));
		assertEquals(FooExtended.class, naming.decodeType(repository, Foo.class,
				"propoid.db.naming.FooExtended"));
		assertEquals(FooExtended.class, naming.decodeType(repository,
				FooExtended.class, "propoid.db.naming.FooExtended"));
	}
}

class Foo extends Propoid {

}

class FooExtended extends Foo {

}