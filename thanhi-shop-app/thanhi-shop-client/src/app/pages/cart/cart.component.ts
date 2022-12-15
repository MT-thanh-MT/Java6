import {Component, DoCheck, OnInit} from '@angular/core';
import {CartService} from "../../core/services/cart.service";
import {Cart} from "../../shared/model/Cart";
import {ToastService} from "../../shared/toast/toast-service";
import {UserService} from "../../core/services/user.service";
import {UserAuthService} from "../../core/services/user-auth.service";
import {OrderService} from "../../core/services/order.service";
import {Order, OrderDetail} from "../../shared/model/Order";
import {Status} from "../../shared/model/Status";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit, DoCheck {
  showCheckOut: boolean = false;
  isHasItem: boolean = true;

  constructor(public cartService: CartService,
              public orderService: OrderService,
              public toast: ToastService,
              public userAuthService: UserAuthService,
              private router: Router) {
    this.isHasItem = this.cartService.shoppingCart.length <= 0;
  }

  ngDoCheck(): void {
    this.isHasItem = this.cartService.shoppingCart.length <= 0;
    if (this.isHasItem) {
      this.showCheckOut = false;
    }
  }

  ngOnInit(): void {
  }

  visible = false;

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }

  getWidth(): number {
    let mediaWidth = screen.width;
    if (mediaWidth >= 720) {
      return mediaWidth * .7;
    } else {
      return mediaWidth;
    }
  }

  checkOut() {
    this.showCheckOut = !this.showCheckOut;
  }

  clearAll() {
    this.cartService.clearCart();
  }

  changeQuantity(input: HTMLInputElement, item: Cart) {
    let productQuantity = item.product?.quantity;
    let currentQuantity = item.quantity;
    let changeQuantity = parseInt(input.value);
    // @ts-ignore
    if (productQuantity < changeQuantity) {
      input.value = String(currentQuantity);
      this.toast.show('INSUFFICIENT QUANTITY', {classname: 'bg-danger text-light', delay: 5000});
      return;
    } else if (changeQuantity < 1) {
      input.value = String(1);
      this.toast.show('PLEASE, CHOOSE AT LEAST ONE PRODUCT', {classname: 'bg-danger text-light', delay: 5000});
      return;
    } else {
      item.quantity = changeQuantity;
      this.cartService.saveToLocalStorage();
    }
  }

  purchase(address: string) {

    this.orderService.createOrder(this.toOrder(address)).subscribe(
      (respone: any) => {
        this.cartService.clearCart();
        this.toast.show('Order Success', { classname: 'bg-success text-light', delay: 5000 });
      },
        (err) => {
          console.log(err);
          if (err.status == 401) {
            this.router.navigate(['/login']);
            this.toast.show('Please sing in to buy something!', { classname: 'bg-danger text-light', delay: 5000 });
          } else {
            this.toast.show('Order fail something went wrong!', { classname: 'bg-danger text-light', delay: 5000 });
          }
        }
    );
  }

  /* Convert shopping cart data to Order data */
  private toOrder(address: string): Order {
    let order: Order = new Order();
    let orderDetails: OrderDetail[] = [];
    order.accountId = this.userAuthService.getUserDetail().id;
    order.address = address;
    order.status = new Status("WAITING ACCEPT");
    order.createBy = this.userAuthService.getUserDetail().username;
    order.createDate = new Date();
    this.cartService.shoppingCart.forEach(cart => {
      let orderDetail: OrderDetail = new OrderDetail();
      orderDetail.productId = cart.product?.id;
      orderDetail.price = this.cartService.getItemTotalPrice(cart);
      orderDetail.quantity = cart.quantity;
      orderDetails.push(orderDetail);
    });
    order.orderDetails = orderDetails;

    return order;
  }
}
