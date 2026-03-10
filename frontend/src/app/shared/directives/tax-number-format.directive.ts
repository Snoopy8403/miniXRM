import { Directive, HostListener, ElementRef } from '@angular/core';

@Directive({
  selector: '[appTaxNumberFormat]',
})
export class TaxNumberFormatDirective {
  constructor(private el: ElementRef<HTMLInputElement>) {}

  @HostListener('input', ['$event'])
  onInput(event: Event) {
    const input = this.el.nativeElement;

    const digits = input.value.replace(/\D/g, '');

    let formatted = digits;

    if (digits.length > 8) {
      formatted = digits.slice(0, 8) + '-' + digits.slice(8);
    }

    if (digits.length > 9) {
      formatted =
        digits.slice(0, 8) +
        '-' +
        digits.slice(8, 9) +
        '-' +
        digits.slice(9, 11);
    }

    input.value = formatted;
  }
}
