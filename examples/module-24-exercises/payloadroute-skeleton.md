# Lab 24 — PayloadRoot Skeleton

## Class annotation
@Endpoint \
class CustomerEndpoint

## @PayloadRoot localPart
@PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")

## Method inputs/outputs
method getCustomer(@RequestPayload GetCustomerRequest req)


## Delegation line (words)
Request → Map → CustomerService → Map → Response

## Scope
Pre-lab only.