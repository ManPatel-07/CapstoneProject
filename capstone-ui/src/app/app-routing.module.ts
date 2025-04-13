import { TestUiComponent } from './componentes/test-ui/test-ui.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DummyProfileComponent } from './components/dummy-profile/dummy-profile.component';
import { AuthGuard } from './components/guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'signup', pathMatch: 'full' }, // Default route to signup
  { path: 'signup', component: SignupComponent }, // Open route
  { path: 'login', component: LoginComponent }, // Open route
  { path: 'profile', component: TestUiComponent, canActivate: [AuthGuard] }, // Protected route
  { path: '**', redirectTo: 'signup' } // Redirect unknown routes to signup
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
