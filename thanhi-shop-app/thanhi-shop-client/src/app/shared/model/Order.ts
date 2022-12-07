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

/* Order view model */
export class OrderView extends BaseEntity {
  public accountId?: number;
  public payer?: string;
  public address?: string;
  public status?: string;
  public orderDetailView?: OrderDetailView[] = [];

  constructor(accountId?: number,
              address?: string,
              payer?: string,
              status?: string,
              orderDetailView?: OrderDetailView[]) {
    super();
    this.accountId = accountId;
    this.payer = payer;
    this.address = address;
    this.status = status;
    this.orderDetailView = orderDetailView;
  }

}

/* Order detail model */
export class OrderDetailView extends BaseEntity {
  public orderId?: number;
  public productId?: number;
  public productImageUrl?: string;
  public productName?: string;
  public subCategoryID?: number;
  public subCategoryName?: string;
  public price?: number;
  public quantity?: number;

  constructor(orderId?: number,
              productId?: number,
              productImageUrl?: string,
              productName?: string,
              price?: number,
              subCategoryID?: number,
              subCategoryName?: string,
              quantity?: number) {
    super();
    this.orderId = orderId;
    this.productId = productId;
    this.productImageUrl = productImageUrl;
    this.productName = productName;
    this.subCategoryName = subCategoryName;
    this.subCategoryID = subCategoryID;
    this.price = price;
    this.quantity = quantity;
  }
}
