# SOAP design notes — Lab 13

## TODO
1. Contract-first vs code-first for partners
2. Document/literal choice
3. Correlation placement (`lab-request-001`)
4. Fault shapes: not-found vs validation
5. What Lab 24 will host vs what stays static here

## Discussion Questions
Main data flow (partner → SOAP contract → future endpoint → CustomerService)
Trust boundary: schema validation vs service business rules
Success/failure contract for GetCustomer unknown IDs
Stable identity (CUS-1001) vs display fields
Retry/idempotency: Create vs Get vs Update semantics
Static WSDL files vs generating WSDL only at runtime
Correlation header/field for support (lab-request-001)
Two instances serving the same WSDL version—what must stay identical?
Why document/literal over RPC/encoded for this lab?
What must not change between Lab 13 and Lab 24 without a version bump?

## Failure Experiments
