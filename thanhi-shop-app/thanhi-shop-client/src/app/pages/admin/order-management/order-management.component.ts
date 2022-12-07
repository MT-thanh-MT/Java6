import { Component, OnInit } from '@angular/core';
import {FormGroup} from "@angular/forms";
import {Order} from "../../../shared/model/Order";
import {OrderManagerService} from "../../../core/services/order-manager.service";
import {log} from "ng-zorro-antd/core/logger";
import {ToastService} from "../../../shared/toast/toast-service";

@Component({
  selector: 'app-order-management',
  templateUrl: './order-management.component.html',
  styleUrls: ['./order-management.component.css']
})
export class OrderManagementComponent implements OnInit {

  page = 1;
  pageSize = 5;
  orders: Order[] = [];
  order!: Order;
  imageUrl: string = "a43bfe0a.jpg";
  modalTitle: string = 'Modal';
  form!: FormGroup;
  loading = false;
  submitted = false;

  constructor(private orderManagerService: OrderManagerService,
              public toast: ToastService,
              ) { }

  ngOnInit(): void {
    this.load("WAITING ACCEPT");
  }

  private load(status: string) {
    this.orderManagerService.getOrderByStatus(status).subscribe(
      (res) => {
        this.orders = res;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  searchByNameOrId(value: string) {

  }

  cancel(order: Order) {
    order.status!.name = 'CANCELLED';
    this.updateStatus(order);
  }

  private updateStatus(order: Order) {
    this.orderManagerService.update(order).subscribe(
      (res) => {
        this.orders.forEach(o => {
          if (o.id == res.id) {
            o = res;
          }
        });
        this.toast.show('Update status success', { classname: 'bg-success text-light', delay: 3000 })
      },
      (error) => console.log(error)
    )
  }

  accept(order: Order) {
    order.status!.name = 'PAID';
    this.updateStatus(order);
  }

  getClassByStatus(name: string) {
    if (name == 'WAITING ACCEPT') {
      return 'text-warning';
    } else if (name == 'PAID') {
      return 'text-success';
    }
    else if (name == 'CANCELLED') {
      return 'text-danger';
    } else return '';
  }

  getOrderByStatus(value: string) {
    this.load(value);
  }
}
