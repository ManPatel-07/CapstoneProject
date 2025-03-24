import { AfterViewInit, Component, ElementRef, Renderer2, ViewChild } from '@angular/core';

declare var bootstrap: any; // Declare Bootstrap globally if needed

@Component({
  selector: 'app-test-ui',
  templateUrl: './test-ui.component.html',
  styleUrl: './test-ui.component.css'
})
export class TestUiComponent {

  @ViewChild('sideNav', { static: false }) sideNav!: ElementRef;
  @ViewChild('navbarToggler', { static: false }) navbarToggler!: ElementRef;

  constructor(private renderer: Renderer2) {}

  ngAfterViewInit(): void {
    // Activate Bootstrap scrollspy on the main nav element
    if (this.sideNav) {
      new bootstrap.ScrollSpy(document.body, {
        target: '#sideNav',
        rootMargin: '0px 0px -40%',
      });
    }

    // Collapse responsive navbar when toggler is visible
    const responsiveNavItems = document.querySelectorAll('#navbarResponsive .nav-link');

    responsiveNavItems.forEach(navItem => {
      this.renderer.listen(navItem, 'click', () => {
        if (this.navbarToggler && window.getComputedStyle(this.navbarToggler.nativeElement).display !== 'none') {
          this.navbarToggler.nativeElement.click();
        }
      });
    });
  }
}
