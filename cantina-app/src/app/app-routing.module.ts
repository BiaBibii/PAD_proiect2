import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ContactComponent} from "./contact/contact.component";
import {DespreComponent} from "./despre/despre.component";
import {AcasaComponent} from "./acasa/acasa.component";

const routes: Routes = [
  { path: '',redirectTo:'acasa', pathMatch:'full'},
  { path: 'acasa', component: AcasaComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'despre', component: DespreComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
