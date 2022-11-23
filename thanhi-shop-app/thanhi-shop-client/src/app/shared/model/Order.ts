import {BaseEntity} from "./BaseEntity";
import {Status} from "./Status";

/* Order model */
export class Order extends BaseEntity {
  public accountId?: number;
  public address?: string;
  public status?: Status;
  public orderDetails?: OrderDetail[] = [];

  constructor(accountId?: number,
              address?: string,
              status?: Status,
              orderDetails?: OrderDetail[]) {
    super();
    this.accountId = accountId;
    this.address = address;
    this.status = status;
    this.orderDetails = orderDetails;
  }

}

/* Order detail model */
export class OrderDetail extends BaseEntity {
  public orderId?: number;
  public productId?: number;
  public price?: number;
  public quantity?: number;

  constructor(orderId?: number,
              productId?: number,
              price?: number,
              quantity?: number) {
    super();
    this.orderId = orderId;
    this.productId = productId;
    this.price = price;
    this.quantity = quantity;
  }
}
