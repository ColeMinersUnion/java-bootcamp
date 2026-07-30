
//
/**
 * Traditional Customer class for Northstar CRM.
 * Demonstrates mutable customer representation.
 */
public class Customer {
    private String id;
    private String fullName;
    private String status;

    public Customer() {}

    public Customer(String id, String fullName, String status) {
        this.id = id;
        this.fullName = fullName;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (fullName != null ? !fullName.equals(customer.fullName) : customer.fullName != null) return false;
        return status != null ? status.equals(customer.status) : customer.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

