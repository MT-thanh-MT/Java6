import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './pages/home/home.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { ProductComponent } from './pages/admin/product/product.component';
import { UserManagementComponent } from './pages/admin/user-management/user-management.component';
import { AppRoutingModule } from './app-routing.module';
import {RouterModule} from "@angular/router";
import { LoginComponent } from './pages/account/login/login.component';
import { SignUpComponent } from './pages/account/sign-up/sign-up.component';
import { ProductListComponent } from './pages/product/product-list/product-list.component';
import { ProductDetailComponent } from './pages/product/product-detail/product-detail.component';
import { BodyComponent } from './pages/admin/body/body.component';
import { AdminComponent } from './pages/admin/admin/admin.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomePageComponent } from './pages/home/home-page/home-page.component';
import {MatToolbar,MatToolbarModule, } from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {RoleGuardService} from "./core/auth/role-guard";
import {UserAuthService} from "./core/services/user-auth.service";
import {ToastsContainer} from "./shared/toast/toast-container.component";
import { AccessDeniedComponent } from './error/access-denied/access-denied.component';
import { CartComponent } from './pages/cart/cart.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import {NzGridModule} from "ng-zorro-antd/grid";
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzDatePickerModule} from "ng-zorro-antd/date-picker";
import {NzInputModule} from "ng-zorro-antd/input";
import { CategoryComponent } from './pages/category/category.component';
import {CartService} from "./core/services/cart.service";
import {OrderService} from "./core/services/order.service";
import {RequestHeaderConstants} from "./core/services/requestHeader.constants";
import {NzPopoverModule} from "ng-zorro-antd/popover";
import { ProfileComponent } from './pages/account/profile/profile.component';
import {SweetAlert2Module} from "@sweetalert2/ngx-sweetalert2";
import {NzSwitchModule} from "ng-zorro-antd/switch";
import { OrderManagementComponent } from './pages/admin/order-management/order-management.component';
import { OrderComponent } from './pages/order/order.component';
import { AboutComponent } from './pages/about/about.component';
import { ChangePasswordComponent } from './pages/account/change-password/change-password.component';
import { OrderViewComponent } from './shared/order-view/order-view.component';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    AdminDashboardComponent,
    ProductComponent,
    UserManagementComponent,
    LoginComponent,
    SignUpComponent,
    ProductListComponent,
    ProductDetailComponent,
    BodyComponent,
    AdminComponent,
    PageNotFoundComponent,
    HomePageComponent,
    ToastsContainer,
    AccessDeniedComponent,
    CartComponent,
    CategoryComponent,
    ProfileComponent,
    OrderManagementComponent,
    OrderComponent,
    AboutComponent,
    ChangePasswordComponent,
    OrderViewComponent
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        NgbModule,
        AppRoutingModule,
        RouterModule,
        MatToolbarModule,
        MatButtonModule,
        MatIconModule,
        FormsModule,
        HttpClientModule,
        NzDrawerModule,
        NzGridModule,
        NzSelectModule,
        NzDatePickerModule,
        NzInputModule,
        NzPopoverModule,
        ReactiveFormsModule,
        SweetAlert2Module,
        SweetAlert2Module.forRoot(),
        NzSwitchModule,
    ],
  providers: [
    NavbarComponent,
    CategoryComponent,
    ProductListComponent,
    RoleGuardService,
    UserAuthService,
    CartService,
    OrderService,
    RequestHeaderConstants,
    { provide: NZ_I18N, useValue: en_US }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
