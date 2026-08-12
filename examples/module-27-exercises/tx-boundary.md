# Lab 27 — TX boundary

Place: @Transactional → TransferService.transfer(...)
Avoid: @Transactional on Controller
Self-invocation: this.transfer() skips proxy
