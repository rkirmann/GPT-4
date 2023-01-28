import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SectorsComponent} from "./component/sectors/sectors.component";

const routes: Routes = [
  {path: '', component: SectorsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
