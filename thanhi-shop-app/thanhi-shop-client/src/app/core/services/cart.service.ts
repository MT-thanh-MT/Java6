import {Injectable, OnInit} from '@angular/core';
import {Cart} from "../../shared/model/Cart";
import {Product} from "../../shared/model/Product";
import {ToastService} from "../../shared/toast/toast-service";

@Injectable({
  providedIn: 'root'
})
export class CartService implements OnInit {
  public cartTotal: number = 0;
  public shoppingCart: Cart[] = [];

  constructor(private toast: ToastService) {
    this.getCartFormLocalStrorage();
  }

  ngOnInit(): void {
  }

  public setTotal(quantity: number) {
    this.cartTotal += quantity;
  }

  public addToCart(p: Product, qty: number) {
    let item = this.shoppingCart.find(item => item.product?.id == p.id);
    if (item) {
      // @ts-ignore
      if (item.product?.quantity <= item.quantity) {
        this.toast.show('INSUFFICIENT QUANTITY', {classname: 'bg-danger text-light', delay: 5000});
      } else if (item.product?.quantity! < (item.quantity! + qty)){
        this.toast.show('INSUFFICIENT QUANTITY', {classname: 'bg-danger text-light', delay: 5000});
      }else {
        // @ts-ignore
        item.quantity += qty;
        this.toast.show('Added to cart successfully', {classname: 'bg-success text-light', delay: 5000});
      }
    } else {
      this.shoppingCart.push(new Cart(p, qty));
      this.toast.show('Added to cart successfully', {classname: 'bg-success text-light', delay: 5000});
    }
    this.saveToLocalStorage();
  }

  public getCartFormLocalStrorage() {
    let json = localStorage.getItem("shoppingCart");
    this.shoppingCart = json ? JSON.parse(json) : [];
  }

  public clearCart() {
    this.shoppingCart = [];
    this.saveToLocalStorage();
  }

  public getAmount() {
    let amount: number = 0;
    // @ts-ignore
    this.shoppingCart.forEach(({quantity}) => amount = amount + quantity);
    return amount;
  }

  getTotalPrice() {
    let totalPrice = 0;
    this.shoppingCart.forEach(item => {
      // @ts-ignore
      totalPrice += (item.product?.sellPrice * item.quantity);
    });
    return totalPrice;
  }

  getItemTotalPrice(item: Cart) {
    // @ts-ignore
    return item.product?.sellPrice * item.quantity;
  }

  removeItem(item: Cart) {
    let index = this.shoppingCart.findIndex(cartItem => cartItem.product?.id === item.product?.id);
    this.shoppingCart.splice(index, 1);
    this.saveToLocalStorage();
  }

  public saveToLocalStorage() {
    localStorage.setItem('shoppingCart', JSON.stringify(this.shoppingCart));
  }
}
