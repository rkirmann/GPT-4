import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./component/main/main.component";
import {ImageComponent} from "./component/image/image.component";
import {ImageVariationComponent} from "./component/image-variation/image-variation.component";

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'image', component: ImageComponent},
  {path: 'image-variation', component: ImageVariationComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
