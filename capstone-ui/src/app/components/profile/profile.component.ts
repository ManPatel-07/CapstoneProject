import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';

declare var bootstrap: any; // Declare Bootstrap globally

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  @ViewChild('sideNav', { static: false }) sideNav!: ElementRef;
  @ViewChild('navbarToggler', { static: false }) navbarToggler!: ElementRef;

  constructor(private elementRef: ElementRef) {}

  ngAfterViewInit(): void {
    // Activate Bootstrap scrollspy on the main nav element
    if (this.sideNav) {
      new bootstrap.ScrollSpy(document.body, {
        target: '#sideNav',
        rootMargin: '0px 0px -40%',
      });
    }

    // Collapse responsive navbar when toggler is visible
    const responsiveNavItems = this.elementRef.nativeElement.querySelectorAll('#navbarResponsive .nav-link');
    responsiveNavItems.forEach((responsiveNavItem: any) => {
      responsiveNavItem.addEventListener('click', () => {
        if (window.getComputedStyle(this.navbarToggler.nativeElement).display !== 'none') {
          this.navbarToggler.nativeElement.click();
        }
      });
    });
  }
}
