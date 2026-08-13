import type { CustomerStatus } from '../types/customer'

const labels: Record<CustomerStatus, string> = {
  PROSPECT: "Prospect",
  ACTIVE: "Active",
  CLOSED: "Closed",
};

export default function StatusBadge({ status }: { status: CustomerStatus }) {
  return (
    <span className={`status status--${status.toLowerCase()}`}>
      {labels[status]}
    </span>
  );
}
