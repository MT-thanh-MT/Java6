import {BaseEntity} from "./BaseEntity";
import {OrderDetail} from "./Order";

/* Product model */
export class Product extends BaseEntity{
  public name?: string;
  public imageUrl?: string;
  public sellPrice?: number = 0;
  public originPrice?: number = 0;
  public statusId?: string;
  public subCateId?: number;
  public hot?: Date;
  public quantity?: number=0;
  public description?: string;
  public sold?: number;
  public orderDetailList?: OrderDetail[];

  constructor( name?: string,
               imageUrl?: string,
               sellPrice?: number,
               originPrice?: number,
               statusId?: string,
               subCateId?: number,
               hot?: Date,
               quantity?: number,
               description?: string,
               sold?: number,
               orderDetailList?: OrderDetail[]) {
    super();
    this.name = name;
    this.imageUrl = imageUrl;
    this.sellPrice = sellPrice;
    this.originPrice = originPrice;
    this.statusId = statusId;
    this.subCateId = subCateId;
    this.hot = hot;
    this.quantity = quantity;
    this.description = description;
    this.sold = sold;
    this.orderDetailList = orderDetailList;
  }
}
