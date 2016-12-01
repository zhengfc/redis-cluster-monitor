// Avoid `console` errors in browsers that lack a console.
(function() {
    var method;
    var noop = function () {};
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = (window.console = window.console || {});

    while (length--) {
        method = methods[length];

        // Only stub undefined methods.
        if (!console[method]) {
            console[method] = noop;
        }
    }
}());

// Place any jQuery/helper plugins in here.
/**
 * form autofill (jQuery plugin)
 * Version: 0.1
 * Released: 2011-11-30
 * 
 * Copyright (c) 2011 Creative Area
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Require jQuery
 * http://jquery.com/
 * Copyright 2011, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 */
(function($) {
	$.fn.extend({
		fillForm : function(data, options) {
			var settings = {
				findbyname : true,
				restrict : true
			}, self = this;

			if (options) {
				$.extend(settings, options);
			}

			return this.each(function() {
				$.each(data, function(k, v) {
					// switch case findbyname / findbyid
					var selector, elt;
					if (settings.findbyname) { // by name
						selector = '[name="' + k + '"]';
						elt = (settings.restrict) ? self.find(selector) : $(selector);
						if (elt.length == 1) {
							elt.val((elt.attr("type") == "checkbox") ? [ v ] : v);
						} else if (elt.length > 1) {
							// radio
							elt.val([ v ]);
						} else {
							selector = '[name="' + k + '[]"]';
							elt = (settings.restrict) ? self.find(selector) : $(selector);
							elt.each(function() {
								console.log($(this) + ", " + v)
								$(this).val(v);
							});
						}
					} else { // by id
						selector = '#' + k;
						elt = (settings.restrict) ? self.find(selector) : $(selector);
						if (elt.length == 1) {
							elt.val((elt.attr("type") == "checkbox") ? [ v ] : v);
						} else {
							var radiofound = false;
							// radio
							elt = (settings.restrict) ? self.find('input:radio[name="' + k + '"]') : $('input:radio[name="' + k + '"]');
							elt.each(function() {
								radiofound = true;
								if (this.value == v) {
									this.checked = true;
								}
							});
							// multi checkbox
							if (!radiofound) {
								elt = (settings.restrict) ? self.find('input:checkbox[name="' + k + '[]"]') : $('input:checkbox[name="' + k + '[]"]');
								elt.each(function() {
									$(this).val(v);
								});
							}
						}
					}
				});
			});
		}
	});
	
	$.fn.extend({
		fillTable : function(data, options) {
			var settings = {
				findby : "id",
				restrict : true
			}, self = this;

			if (options) {
				$.extend(settings, options);
			}

			return this.each(function() {
				$.each(data, function(k, v) {
					var selector, elt;
					if (settings.findby=='name') { // by name
						selector = '[name="' + k + '"]';
					} else if(settings.findby=='class'){ // by class
						selector = '.' + k;
					} else { // by id
						selector = '#' + k;
					}
					elt = (settings.restrict) ? self.find(selector) : $(selector);
					elt.text(v);
				});
			});
		}
	});
})(jQuery);
