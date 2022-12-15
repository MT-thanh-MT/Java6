import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/account/login/login.component";
import {AdminDashboardComponent} from "./pages/admin/admin-dashboard/admin-dashboard.component";
import {ProductComponent} from "./pages/admin/product/product.component";
import {HomeComponent} from "./pages/home/home.component";
import {UserManagementComponent} from "./pages/admin/user-management/user-management.component";
import {ProductListComponent} from "./pages/product/product-list/product-list.component";
import {ProductDetailComponent} from "./pages/product/product-detail/product-detail.component";
import {AdminComponent} from "./pages/admin/admin/admin.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {HomePageComponent} from "./pages/home/home-page/home-page.component";
import {RoleGuardService} from "./core/auth/role-guard";
import {AccessDeniedComponent} from "./error/access-denied/access-denied.component";
import {SignUpComponent} from "./pages/account/sign-up/sign-up.component";
import {AboutComponent} from "./pages/about/about.component";
import {OrderComponent} from "./pages/order/order.component";
import {OrderManagementComponent} from "./pages/admin/order-management/order-management.component";
import {ProfileComponent} from "./pages/account/profile/profile.component";
import {ChangePasswordComponent} from "./pages/account/change-password/change-password.component";



const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: "full"
  },
  {
    path: '',
    component:HomeComponent,
    children: [
      {
        path: 'home',
        component: HomePageComponent,
      },
      {path: 'about', component:AboutComponent},
      {path: 'profile', component:ProfileComponent},
      {path: 'change-password', component:ChangePasswordComponent},
      {path: 'orders', component:OrderComponent},
      {
        path: 'product-list',
        component: ProductListComponent,
      },
      {
        path: 'product-detail/:id',
        title: 'Product-detail',
        component: ProductDetailComponent,
      },
    ],

  },
  {
    path: 'admin',
    component:AdminComponent,
    canActivate: [RoleGuardService],
    data: { 'roles': ['ADMIN', 'STAFF']},
    children: [
      {
        path: 'dashboard',
        component: AdminDashboardComponent,
      },
      {
        path: 'product',
        title: 'product-management',
        component: ProductComponent,
      },
      {
        path: 'order-management',
        title: 'order-management',
        component: OrderManagementComponent,
      },
      {
        path: 'user-management',
        title: 'user-management',
        component: UserManagementComponent,
        canActivate: [RoleGuardService],
        data: { 'roles': ['ADMIN']},
      },
    ],
  },
  {path: 'login', component:LoginComponent},
  {path: 'register', component:SignUpComponent},
  {path: 'access-denied', component: AccessDeniedComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
