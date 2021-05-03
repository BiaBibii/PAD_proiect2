import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContactComponent} from "./contact/contact.component";
import {AcasaComponent} from "./acasa/acasa.component";
import {LoginComponent} from "./login/login.component";
import {RegisterFormComponent} from "./register-form/register-form.component";
import {ForgetPasswordComponent} from "./forget-password/forget-password.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {AdminComponent} from "./admin/admin.component";
import {AddProductComponent} from "./add-product/add-product.component";
import {EditProductComponent} from "./table-edit-product/edit-product.component";
import {LoginAuthGuard} from "./login-auth.guard";
import {ProductsListComponent} from "./menu/products-list.component";
import {CartComponent} from "./cart/cart.component";
import {ProductComponent} from "./product/product.component";

const routes: Routes = [
  {path: '', redirectTo: 'acasa', pathMatch: 'full'},
  {path: 'acasa', component: AcasaComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterFormComponent},
  {path: 'forget-password', component: ForgetPasswordComponent},
  {path: 'menu', component: ProductsListComponent},
  {path: 'product/:id', component: ProductComponent},
  {path: 'my-cart', component: CartComponent},

  // { path: '**', component: PageNotFoundComponent},
  {
    path: 'admin',
    canActivate: [LoginAuthGuard],
    data: {
      role: 'ROLE_ADMIN'
    },
    children: [
      {path: '', component: AdminComponent},
      {path: 'add-product', component: AddProductComponent},
      {path: 'edit-products/:id', component: EditProductComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
