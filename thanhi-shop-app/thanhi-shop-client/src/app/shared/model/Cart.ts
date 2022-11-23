import {Product} from "./Product";

export class Cart {
  public product?: Product;
  public quantity?: number=0;


  constructor( product?: Product,
               quantity?: number,) {
    this.product = product;
    this.quantity = quantity;
  }
}
