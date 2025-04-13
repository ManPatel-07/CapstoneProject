import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = { email: '', password: '' };

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    if (!this.loginData.email || !this.loginData.password) {
      alert('Please enter both email and password');
      return;
    }

    this.authService.login(this.loginData).subscribe(
      (response: any) => {
        console.log('Login successful:', response);
        
        // Store JWT token securely
        localStorage.setItem('token', response.accessToken);
        
        // Navigate to profile page after successful login
        this.router.navigate(['/profile']);
      },
      (error) => {
        console.error('Login failed:', error);
        alert('Invalid email or password. Please try again.');
      }
    );
  }
}
