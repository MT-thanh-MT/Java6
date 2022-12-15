import {Component, Input, OnInit} from '@angular/core';
import {OrderView} from "../model/Order";
import {animate, animateChild, keyframes, query, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-order-view',
  templateUrl: './order-view.component.html',
  styleUrls: ['./order-view.component.css'],
  animations: [
    trigger('ngIfAnimation', [
      transition(':enter, :leave', [
        query('@*', animateChild())
      ])
    ]),
    trigger('easeInOut', [
      transition('void => *', [
        style({
          height: '0',
          // transform: 'translateY(-70%)'
        }),
        animate("1500ms ease", style({
          height: '100%',
          transition: 'all'
          // transform: 'translateY(0%)'
        }))
      ]),
      transition('* => void', [
        style({
          height: '100%',
          // transform: 'translateY(0%)'
        }),
        animate("350ms ease", style({
          height: '0',
          transition: 'all'
          // transform: 'translateY(-70%)'
        }))
      ])
    ])
  ],
})
export class OrderViewComponent implements OnInit {

  @Input() orderView!: OrderView;
  showDetaiTitle: string = 'Show detail';
  collapsed = false;
  currentOrderId: number = 0;
  isOpen = false;

  toggle() {
    this.isOpen = !this.isOpen;

    if (this.isOpen) {
      this.showDetaiTitle = 'close';
    } else {
      this.showDetaiTitle = 'Show detail';
    }
  }

  constructor() { }

  ngOnInit(): void {
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
