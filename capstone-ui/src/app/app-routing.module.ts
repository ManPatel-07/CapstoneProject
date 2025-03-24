import { TestUiComponent } from './componentes/test-ui/test-ui.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DummyProfileComponent } from './components/dummy-profile/dummy-profile.component';

const routes: Routes = [
  { path: '', redirectTo: 'signup', pathMatch: 'full' }, // Default route to signup
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'profile', component: TestUiComponent },
  { path: '**', redirectTo: 'signup' } // Redirect unknown routes to signup
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
