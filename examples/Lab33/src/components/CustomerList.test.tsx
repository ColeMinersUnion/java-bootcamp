import { render, screen } from '@testing-library/react'
import { CustomerList } from './CustomerList'
import { seedCustomers } from '../data/seedCustomers'
import {userEvent} from "@testing-library/user-event/dist/cjs/setup/index.js";
import { Customer } from '../types/customer.ts';

describe('CustomerList', () => {
  it("reports the selected customer", async () => {
    const amina = seedCustomers[0];
    const user = userEvent.setup();
    const onEdit = vi.fn();
    render(<CustomerList customers={[amina]} onEdit={onEdit} />);
    await user.click(screen.getByRole("button", { name: "Edit" }));
    expect(onEdit).toHaveBeenCalledWith("CUS-1001");
  });
})
