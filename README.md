Propoid
=======

Propoid is a small library to support properties in Java:

- small footprint
- very simple to use: distributed as jars, no code generation, no effect on tool chain, no setup required
- negligible runtime overhead through reflection on application startup (i.e. when classes are loaded)
- very small memory overhead for each propoid
- see propoid-core for caveats

propoid-db
----------

Simple but powerful solution to persist your propoids in Sqlite.

- No creation of table schema needed, just insert your objects

    Foo foo = new Foo();
    repository.insert(foo);

- Use inheritance (single-table mapping)
- Relations are lazily loaded (many-to-one)
- Typed queries

    Foo foo = new Foo();
    repository.query(foo, Where.equal(foo.bar, "my bar")).first();

- Efficiently iterate over matched propoids (backed by a cursor)

    Foo foo = new Foo();
    for (Foo match : repository.query(foo, Where.equal(foo.bar, "my bar")).list(Order.ascending(foo.baz)) {
        match.baz();
    };

- Perform mass updates

    Foo foo = new Foo();
    repository.query(foo).set(foo.bar, "my bar");

- Table schema is altered for new properties automagically.

propoid-validation
------------------

Add validation to your objects:

    Foo foo = new Foo();
    new MinLengthValidator(foo.bar, R.string.bar_min_length, 4);

propoid-ui
----------

Bind properties to views:

    Foo foo = new Foo();
    new TextBinding(foo.bar, editText);

- all changes are automatically synced between property and a view, bidirectional!
- use one of default converters or add your own
- handles conversion and validation errors automatically
- bind ListView to matched propoids (backed by a cursor)

      listView.setAdapater(new GenericAdapter(repository.query(Foo.class)));

propoid-core
------------

To benefit from these features your objects have to adhere to the following restrictions:

- extend propoid.core.Propoid
- add a default constructor
- use propoid.core.Property for all properties

    public class Foo {
      public final Property bar = property();

      public Foo() {
      }
    }

