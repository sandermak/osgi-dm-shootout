Things to figure out:
- proxies
- whiteboard pattern usage
- how to get filters working with @Bind/@Unbind annotations
- what is the difference between @Property and @ServiceProperty



Gogo shell commands:

instances		displays component instances and their validity





Core mechanism:
Components versus instances. Component is just a type declaration and does not automatically result in an instance being created.
To create an instance, use the @Instantiate annotation.

