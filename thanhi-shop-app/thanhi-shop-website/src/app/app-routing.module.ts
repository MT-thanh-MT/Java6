import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {
    path: '',
    component:HomeComponent,
    children: [
      {
        path: 'child-a',  // child route path
        component: LoginComponent,  // child route component that the router renders
      },
      {
        path: 'child-b',
        title: 'child b',
        component: UserComponent,  // another child route component that the router renders
      },
    ],

  },
  {
    path: 'admin',
    component:AdminComponent,
    children: [
      {
        path: 'product',  // child route path
        component: LoginComponent,  // child route component that the router renders
      },
      {
        path: 'order',
        title: 'Oder',
        component: UserComponent,  // another child route component that the router renders
      },
    ],
  },
  {path: 'forbidden', component:ForbiddenComponent},
  {path: 'login', component:LoginComponent},
  {path: 'user', component:UserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
