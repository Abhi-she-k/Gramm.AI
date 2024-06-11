/*!
* Start Bootstrap - Freelancer v7.0.7 (https://startbootstrap.com/theme/freelancer)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-freelancer/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            rootMargin: '0px 0px -40%',
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});


async function sendData() {
    var text = document.getElementById("userInput").value;
    var output = document.getElementById("output");

    var type = "grammar";

    var gram = document.getElementById("grammar");
    var para = document.getElementById("paraphrase");
    var pro = document.getElementById("pro");
    var ask = document.getElementById("ask");
    
    if (gram.checked) {
        type = "grammar";
    }
    else if (para.checked) {
        type = "paraphrase";
    }
    else if (pro.checked) {
        type = "pro";
    }
    else if (ask.checked) {
        type = "ask";
    }
    try {
        console.log(type);
        const response = await fetch(`/ask?text=${encodeURIComponent(text)}&type=${encodeURIComponent(type)}`);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.text();
        console.log(data);
        output.textContent = data;
        document.getElementById('scrollto').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Fetch error: ', error);
        output.textContent = `Error: ${error.message}`;
    }
}
