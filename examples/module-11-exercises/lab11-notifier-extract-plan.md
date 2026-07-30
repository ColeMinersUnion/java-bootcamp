I want to plan extracting a notifier collaborator so tests can later isolate side effects.
For example: 
```java
//smell: I/O buried in service 
System.out.println("Activated: " + id);
emailClient.send(customer.getEmail(), ...);
//extract sketch: paper only, do not implement yet
interface CustomerNotifier {
    void notifyActivated(String id);
}

```

### Plan: Extract Customer Notifier
TL;DR — Introduce a CustomerNotifier collaborator interface and replace in-method I/O with calls to it. Provide concrete adapters for console and email and a lightweight test double (or use mocks). This isolates side effects so unit tests can assert behavior without performing I/O.
Steps

Inspect code for buried I/O: search for System.out.println and emailClient.send (start from examples/module-11-exercises/lab11-notifier-extract-plan.md) and identify the service and method (e.g., CustomerService.activate(...)) to change.

Add the interface CustomerNotifier (e.g., create src/.../CustomerNotifier.java) with a single intent-focused method like notifyActivated(String id) (signature decision below).

Change the service to depend on CustomerNotifier (constructor or setter injection). Replace direct I/O lines with customerNotifier.notifyActivated(id) inside the service method (e.g., update CustomerService and CustomerService#activate).

Implement two concrete collaborators:

ConsoleCustomerNotifier — the thin adapter that calls System.out.println(...).

EmailCustomerNotifier — adapter that delegates to existing emailClient.send(...) (wraps current code).

Put these in the same package as the service or in a notifier package (e.g., src/.../notifier/ConsoleCustomerNotifier.java and EmailCustomerNotifier.java).

#### Add a test double and tests:

Create TestCustomerNotifier (in test sources) that records calls, or use a mocking library (Mockito) to verify notifyActivated is invoked.

Write unit tests for CustomerService that inject the test double and assert behavior without performing console/email I/O.

Wire the concrete notifier for runtime:

If you use DI (Spring/Guice), bind CustomerNotifier to ConsoleCustomerNotifier or EmailCustomerNotifier by configuration.

If no DI framework, pass the concrete notifier from the composition root (e.g., main() or CLI bootstrap).

#### Further Considerations

Signature choice: notifyActivated(String id) (minimal) vs notifyActivated(Customer customer) (richer). Option A: prefer String id if notifier only needs id; Option B: prefer Customer when email or personalization is required.

Error handling: decide whether notifier throws checked exceptions or handles errors internally (log and swallow vs bubble up). Prefer logging in adapters and keep service logic simple.

Asynchronous delivery: consider returning a Future/CompletableFuture or making adapters enqueue work later if email should not block the service.

Draft for review — which concrete service class and package should I target (name and file path), and do you prefer String id or Customer as the notifier method parameter?

