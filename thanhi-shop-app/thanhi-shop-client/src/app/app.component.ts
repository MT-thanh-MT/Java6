import {Component, OnDestroy} from '@angular/core';
import {ToastService} from "./shared/toast/toast-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  title = 'thanhi-shop-client';

  constructor(public toastService: ToastService) {
  }

  showStandard(message: String) {
    // @ts-ignore
    this.toastService.show(message);
  }

  showSuccess(message: String) {
    // @ts-ignore
    this.toastService.show(message , {classname: 'bg-success text-light', delay: 5000});
  }

  showDanger(message?: String) {
    // @ts-ignore
    this.toastService.show(message,{classname: 'bg-danger text-light', delay: 5000});
  }

  ngOnDestroy(): void {
    this.toastService.clear();
  }
}
