import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token'); // Get JWT token from localStorage

    // URLs to exclude from authentication
    const excludedUrls = ['/auth/signup', '/auth/login']; 

    // Check if the request URL contains an excluded path
    if (excludedUrls.some(url => req.url.includes(url))) {
      return next.handle(req); // Skip adding token for signup/login
    }

    // If token exists, add Authorization header
    if (token) {
      const cloned = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(cloned);
    }

    return next.handle(req);
  }
}
