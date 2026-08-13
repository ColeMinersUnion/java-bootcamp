import type { Customer } from '../types/customer'
import StatusBadge from './StatusBadge'

export function CustomerCard({
  customer,
  onEdit,
}: {
  customer: Customer
  onEdit: (customerId: string) => void
}) {
  //// TODO: article with name, email, StatusBadge, Edit button calling onEdit(customer.customerId)
  return (
    <article className="card" data-testid={`card-${customer.customerId}`}>

      <p>{customer.fullName}</p>
      <StatusBadge status={customer.status} />
      <p>
        <a href={`mailto:${customer.email}`}>
          Email: {customer.email}
        </a>
      </p>
      <button onClick={onEdit(customer.customerId)}>
        <p>Edit Customer</p>
      </button>
    </article>
  )
}
