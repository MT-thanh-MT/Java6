import {Component, OnInit} from '@angular/core';
import {OrderView} from "../../shared/model/Order";
import {UserAuthService} from "../../core/services/user-auth.service";
import {OrderService} from "../../core/services/order.service";
import {Router} from "@angular/router";
import {ToastService} from "../../shared/toast/toast-service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {

  collapsed = false;
  orderViews: OrderView[] = [];
  currentOrderId: number = 0;
  showDetaiTitle: string = 'Show detail';


  constructor(private userAuthservice: UserAuthService,
              private orderService: OrderService,
              private router: Router,
              private toast: ToastService) {
  }

  ngOnInit(): void {
    let username = this.userAuthservice.getUserDetail().username;
    if (!username) {
      this.router.navigate(['/access-denied']);
    } else {
      this.load(username);
    }

  }


  toggleCollapse(id: number): void {
    this.currentOrderId = id;
    this.collapsed = !this.collapsed;
  }

  private load(username: string) {
    this.orderService.getOrderViewByUsername(username).subscribe(
      (res) => this.orderViews = res,
      (error) => {
        if (error.status == 401) {
          console.log(error);
          this.router.navigate(['/login'])
          this.toast.show('Your session time out! ', { classname: 'bg-danger text-light', delay: 5000 });
        }
      }
    )
  }

  getTotalPrice(orderView: OrderView) {
    return orderView.orderDetailView!.map<number>(o => o.price!).reduce((total, price) => total + price);
  }

  getStatusClass(status: String) {
    if (status == 'WAITING ACCEPT') {
      return 'order-wait';
    } else if (status == 'PAID') {
      return 'order-success';
    }
    else if (status == 'CANCELLED') {
      return 'order-cancelled';
    } else return '';
  }

  openDetail(detail: HTMLDivElement) {
    let className: string = detail.className;

    if (className) {
      detail.setAttribute('class', '')
      this.showDetaiTitle = 'close';
    } else {
      detail.setAttribute('class', 'collapse')
      this.showDetaiTitle = 'Show detail';
    }

  }
}
