/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

AUI().ready('anim-custom', 'aui-rating', 'removable', 'io-form', 'review-form', 'load-more', 'aui-dialog', 'liferay-portlet-url', 'selector-css3', 'accessible-dialog', 'change-spinner', 'json-parse', function(A) {
	
	
	if (A.one('form .rating')) {
		var rating = new A.StarRating({
			boundingBox: 'form .rating',
			disabled: false,
			inputName: 'review-rating'
		});
		rating.render();
		var inputs = rating.get('boundingBox').all('input');
		inputs.on('focus', function (event) {
			var newIndex = inputs.indexOf(event.target);
			if (rating.get('selectedIndex') != newIndex) rating.select(newIndex);
		});
		A.all('form .rating ul').remove();
	}
	
	// click events to show/hide the review form
	var buttons = A.all('.review-course');
	if (buttons) {
		buttons.on('click', function(event) {
			event.preventDefault();
			var form = A.one('#review-form');
			form.one('textarea').focus();
			var pos = A.one('#review-form').getXY();
			var scroll = new A.Anim({
				  node: window
				, duration: .4
				, to: { scroll: [pos[0], pos[1] - 100] }
			});
			scroll.run();
		});
	}
	
	var updateHistogram = function (valArray, reviewCount) {
		var histogram = A.one('.ratings-histogram');
		var total = 0;
		if (!histogram) return;
		if (valArray.length > 0) {
			for (var i = 0; i < valArray.length; i++) {
				total += valArray[i];
			}
			for (var i = 0; i < valArray.length; i++) {
				var thisVal = valArray[i];
				var percent = 0;
				if (total != 0) percent = 100 * (thisVal / total);
				var bar = histogram.one('[data-value="' + (i + 1) + '"] .percent');
				var text = Liferay.Language.get('rating-type-percent', [thisVal, percent]);
				if (!bar) continue;
				bar.setStyle('width', percent + '%');
				bar.text(text);
			}
		} else {
			var zerotext = Liferay.Language.get('rating-type-percent', [0, 0]);
			histogram.all('.percent').each(function () {
				this.setStyle('width', '0%');
				this.text(zerotext);
			});
		}
	}
	
	var updateAverage = function (newAverage, reviewCount) {
		var averages = A.all('#average-rating .aui-starrating, .course-attribute .aui-starrating');
		averages.each(function (ratingBox) {
			ratingBox.empty();
			new A.StarRating({
				boundingBox: ratingBox,
				disabled: true,
				defaultSelected: newAverage
			}).render();
		});
		var countString = 'num-votes-plural';
		if (reviewCount == 0) countString = 'num-votes-zero';
		else if (reviewCount == 1) countString = 'num-votes-singular';
		A.all('*[itemprop="ratingCount"]').text(Liferay.Language.get(countString, [reviewCount]));
	}


	var lists = A.all('.reviewlist');
	if (lists.size() > 0) {
		lists.each(function (list) {
			var reviews = list.all('.review');
			
			var loader = new A.NTER.LoadMore({
				list: list,
				url: list.getAttribute('data-more-url'),
				startParam: list.getAttribute('data-start-param-name'),
				perPage: list.getAttribute('data-per-page'),
				start: list.getAttribute('data-per-page'),
				total: list.getAttribute('data-total')
			});
			loader.render()
			
			// edit button
			list.delegate('click', function (event) {
				event.preventDefault();
				var editButton = event.currentTarget;
				var review = event.currentTarget.ancestor(function (el) { if (el.hasClass('review')) return true; });
				var actions = event.currentTarget.ancestor(function (el) { if (el.hasClass('actions')) return true; });
				var reviewCourseId = review.getAttribute('data-course-id');
				var editRedirect = review.getAttribute('data-redirect-url');
				var submitUrl = Liferay.PortletURL.createActionURL()
						.setPortletId('coursereviews_WAR_ntercatalogportlet')
						.setParameter('redirect', editRedirect)
						.setName('updateCourseReview')
						.setPortletMode('view')
						.setWindowState('normal')
						.toString() + '&cid='+reviewCourseId+'#review';
				var ajaxUrl = Liferay.PortletURL.createActionURL()
						.setPortletId('coursereviews_WAR_ntercatalogportlet')
						.setParameter('ajax', true)
						.setWindowState('EXCLUSIVE')
						.setName('updateCourseReview')
						.toString();
				var form = new A.NTER.ReviewForm({
					boundingBox: review.one('.review-body'),
					portletId: 'coursereviews_WAR_ntercatalogportlet',
					submitUrl: submitUrl,
					ajaxUrl: ajaxUrl,
					dataNodes: {
						content: review.one('.review-text'),
						rating: review.one('.user-rating')
					},
					subjectId: review.getAttribute('data-course-id'),
					reviewId: review.getAttribute('data-review-id')
				}).render();
				form.on('open', function (e) {
					if (actions) actions.fadeOut();
				});
				form.on('close', function (e) {
					if (actions) actions.fadeIn({callback:function(){ editButton.focus(); }});
				});
				form.on('saveSuccess', function (e) {
					var reviewUserId = review.getAttribute('data-user-id');
					var currentUserId = Liferay.ThemeDisplay.getUserId();
					var returnData = form.get('returnData');
					
					// update "your rating" stars
					if (reviewUserId == currentUserId) {
						var ratingBox = A.one('#review .aui-starrating');
						ratingBox.empty();
						var rating = new A.StarRating({
							boundingBox: ratingBox,
							disabled: true,
							defaultSelected: returnData['review-rating']
						}).render();
					}
					
					// update "average rating" stars
					updateAverage(returnData['average-rating']);
					
					// update histogram
					updateHistogram(returnData['review-histogram']);
				});
			}, '.edit');
			
			// remove button
			list.delegate('click', function (event) {
				event.preventDefault();
				var review = event.currentTarget.ancestor(function (el) { if (el.hasClass('review')) return true; });
				var reviewId = review.getAttribute('data-review-id');
				var reviewIdParam = review.getAttribute('data-review-id-param');
				var reviewCourseId = review.getAttribute('data-course-id');
				var reviewCourseIdParam = review.getAttribute('data-course-id-param');
				var reviewUserId = review.getAttribute('data-user-id');
				var currentUserId = Liferay.ThemeDisplay.getUserId();
				var editRedirect = review.getAttribute('data-redirect-url');
				var cancelUrl = Liferay.PortletURL.createActionURL()
					.setPortletId('coursereviews_WAR_ntercatalogportlet')
					.setParameter('ajax', true)
					.setWindowState('EXCLUSIVE')
					.setName('undoDeleteCourseReview')
					.toString();
				if (!review.Removable) {
					review.plug(A.NTER.Removable);
					var itemName = escape(review.one('.reviewer-name').text());
					review.Removable.registerBtn({
						  btnClass: '.delete'
						, text: {
							  successMsg: Liferay.Language.get('review-removed-success', [itemName])
							, errorMsg: Liferay.Language.get('review-removed-error', [itemName])
							, cancelSuccessMsg: Liferay.Language.get('review-removed-cancel-success', [itemName])
							, cancelErrorMsg: Liferay.Language.get('review-removed-cancel-error', [itemName])
							, confirmMsg: Liferay.Language.get('review-removed-confirmation', [itemName])
							, confirmBtn: Liferay.Language.get('remove')
						}
						, confirm: true
						, cancelUrl: cancelUrl
					});
					event.currentTarget.simulate('click');
				}
				
				review.Removable.on('remove', function (event) {
					// "write a review" button
					if (reviewUserId == currentUserId) {
						var reviewArea = A.one('#review .ratings-stats');
						reviewArea.empty();
						var editUrl = Liferay.PortletURL.createRenderURL()
							.setPortletId('coursereviews_WAR_ntercatalogportlet')
							.setParameter(reviewCourseIdParam, reviewCourseId)
							.setParameter('userId', currentUserId)
							.setParameter('jspPage', '/course-reviews/jsp/edit-review.jsp')
							.setParameter('redirect', editRedirect)
							.toString() + '&cid='+reviewCourseId+'#review-form';
						var editButton = A.Node.create('<a href="' + editUrl + '" class="button"></a>');
						editButton.text(Liferay.Language.get('write-a-review'));
						reviewArea.append(Liferay.Language.get('you-have-not-reviewed-this-course'));
						reviewArea.append('<div class="update-review"></div>').append(editButton);
					}
					
					var returnData = review.Removable.get('returnData');
					// update "average rating" stars
					updateAverage(returnData['average-rating'], returnData['review-count']);
					// update histogram
					updateHistogram(returnData['review-histogram'], returnData['review-count']);
				});
				review.Removable.on('unremove', function (event) {
					// "edit review" button and stars
					if (reviewUserId == currentUserId) {
						var reviewArea = A.one('#review .ratings-stats');
						var editButton = reviewArea.one('.button').clone();
						reviewArea.empty();
						editButton.text(Liferay.Language.get('edit-your-review'));
						editButton.setAttribute('href', Liferay.PortletURL.createRenderURL()
															.setPortletId('coursereviews_WAR_ntercatalogportlet')
															.setParameter(reviewIdParam, reviewId)
															.setParameter(reviewCourseIdParam, reviewCourseId)
															.setParameter('userId', currentUserId)
															.setParameter('jspPage', '/course-reviews/jsp/edit-review.jsp')
															.setParameter('redirect', editRedirect)
															.toString() + '&cid='+reviewCourseId+'#review-form');
						reviewArea.append(Liferay.Language.get('your-rating-for-this-course'));
						reviewArea.append('<div class="aui-starrating"></div>');
						reviewArea.append('<div class="update-review"></div>').append(editButton);
						var ratingBox = A.one('#review .aui-starrating');
						var rating = new A.StarRating({
							boundingBox: ratingBox,
							disabled: true,
							defaultSelected: parseInt(review.one('.user-rating').getAttribute('data-rating'))
						}).render();
					}
					
					var returnData = review.Removable.get('returnData');
					// update "average rating" stars
					updateAverage(returnData['average-rating'], returnData['review-count']);
					// update histogram
					updateHistogram(returnData['review-histogram'], returnData['review-count']);
				});
			}, '.delete');

            // hide button
			list.delegate('click', function (event) {
				event.preventDefault();
				var review = event.currentTarget.ancestor(function (el) { if (el.hasClass('review')) return true; });
				var reviewCourseId = review.getAttribute('data-course-id');
				var reviewCourseIdParam = review.getAttribute('data-course-id-param');
				var currentUserId = Liferay.ThemeDisplay.getUserId();
				var editRedirect = review.getAttribute('data-redirect-url');
				if (!review.Removable) {
					review.plug(A.NTER.Removable);
					var itemName = escape(review.one('.reviewer-name').text());
					review.Removable.registerBtn({
						  btnClass: '.hide'
						, showUndoButton: false
						, text: {
							  successMsg: Liferay.Language.get('review-hidden-success', [itemName])
							, errorMsg: Liferay.Language.get('review-hidden-error', [itemName])
							, cancelSuccessMsg: Liferay.Language.get('review-hidden-cancel-success', [itemName])
							, cancelErrorMsg: Liferay.Language.get('review-hidden-cancel-error', [itemName])
							, confirmMsg: Liferay.Language.get('review-hidden-confirmation', [itemName])
							, confirmBtn: Liferay.Language.get('hide')
						}
						, confirm: true
					});
					event.currentTarget.simulate('click');
				}
			}, '.hide');

			// helpfulness buttons
			list.delegate('click', function (e) {
				e.preventDefault();
				var targetButton = e.currentTarget;
				var container = targetButton.ancestor(function (el) {
					if (el.hasClass('metamoderation')) return true;
				});
				var form = targetButton.ancestor(function (el) {
					if (el.get('nodeName').toLowerCase() == 'form') return true;
				});
				var score = form.one('input[name="score"]').item(0);
				if (!container || !score) return;

				container.all('button.selected').removeClass('selected');
				if (score.get('value') != 0) targetButton.addClass('selected');

				var portletId = 'coursereviews_WAR_ntercatalogportlet';
				var url = Liferay.PortletURL.createActionURL();
				url.setName('updateReviewRating').setParameter('ajax',true);
				url.setPortletId(portletId);
				url.setWindowState('EXCLUSIVE');
				A.io(url, {
					method: 'POST',
					form: {id:e.currentTarget.get('parentNode')},
					on: {
						success: function (id, response) {
							try {
								var newVals = A.JSON.parse(response.responseText);
								var downVotes = (newVals.totalEntries - newVals.totalScore) / 2;
								var upVotes = newVals.totalEntries - downVotes;
								var downLabel = upLabel = 'meta-votes';
								if (downVotes == 0) downLabel = 'meta-votes-zero';
								else if (downVotes == 1) downLabel = 'meta-votes-singular';
								if (upVotes == 0) upLabel = 'meta-votes-zero';
								else if (upVotes == 1) upLabel = 'meta-votes-singular';
								container.one('.bad small').setContent(Liferay.Language.get(downLabel, [downVotes]));
								container.one('.good small').setContent(Liferay.Language.get(upLabel, [upVotes]));

								// set up to vote or un-vote when clicked again
								var submittedScore = score.get('value');
								container.one('.bad input[name="score"]').set('value', -1);
								container.one('.good input[name="score"]').set('value', 1);
								if (submittedScore != 0) {
									score.set('value', 0);
								}
							} catch (err) {
								// response was not formatted properly, do nothing
								return;
							}
						},
						failure: function () {
						}
					}
				});
			}, '.good button, .bad button');
			
			// flag button
			list.delegate('click', function (e) {
				e.preventDefault();
				var review = e.currentTarget.ancestor(function (el) { if (el.hasClass('review')) return true; });
				var reviewId = review.getAttribute('data-review-id');
				var reviewIdParam = review.getAttribute('data-review-id-param');
				var reviewCourseId = review.getAttribute('data-course-id');
				var reviewCourseIdParam = review.getAttribute('data-course-id-param');
				var reviewUserId = review.getAttribute('data-user-id');
				var currentUserId = Liferay.ThemeDisplay.getUserId();
				var portletId = 'coursereviews_WAR_ntercatalogportlet';
				var uri = Liferay.PortletURL.createRenderURL()
								 .setPortletId(portletId)
								 .setWindowState('EXCLUSIVE')
								 .setParameter('jspPage', '/course-reviews/jsp/editFlagEntry.jsp')
								 .toString()
								 + '&cid=' + reviewCourseId
								 + '&crid=' + reviewId;
				var popup = new A.NTER.AccessibleDialog(
						{
							centered: true,
							destroyOnClose: true,
							draggable: true,
							modal: true,
							stack: true,
							title: Liferay.Language.get('report-inappropriate-content'),
							width: 435
						}
				).render();
				popup.plug(
						A.Plugin.IO, {
							uri: uri,
							method: 'get',
							after: {
								success: function (load) {
									var form = popup._currFillNode.all('form');
									if (form) {
										form.on('submit', function (submission) {
											submission.preventDefault();
											var ajaxUrl = Liferay.PortletURL.createActionURL()
													.setPortletId('coursereviews_WAR_ntercatalogportlet')
													.setParameter('ajax', true)
													.setWindowState('EXCLUSIVE')
													.setName('createFlagEntry')
													.toString();
											A.io(ajaxUrl, {
												method: 'POST',
												form: {id:form}, 
												on: {
													start: function () { form.plug(A.NTER.ChangeSpinner); },
													complete: function () { form.unplug(A.NTER.ChangeSpinner); },
													success: function (id, response) {
														try {
															var data = A.JSON.parse(response.responseText);
														} catch (e) {
															form.prepend(A.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>'));
															return;
														}
														if (data.success) {
															popup._currFillNode.html('');
															var msg = A.Node.create('<p>' + Liferay.Language.get('flag-thanks') + '</p>');
															var successClose = A.Node.create('<button type="button">'+Liferay.Language.get('close')+'</button>');
															successClose.on('click', function (closeEvent) { popup.close(); });
															popup._currFillNode.append(msg).append(successClose);
														} else {
															form.all('.portlet-msg-error').remove();
															var errors = data.errors;
															for (var i = 0; i < errors.length; i++) {
																form.prepend(A.Node.create('<div class="portlet-msg-error">'+errors[i]+'</div>'));
															}
														}
													},
													failure: function (id, response) {
														form.prepend(A.Node.create('<div class="portlet-msg-error">'+Liferay.Language.get('ajax-fail')+'</div>'));
													}
												}
											});
										});
										form.one('.cancel').on('click', function (cancellation) {
											cancellation.preventDefault();
											popup.close();
										});
									}
								}
							}
						}
				);
				popup.on('destroy', function () { e.currentTarget.focus(); });
			}, 'a.flagEntry');
		});
	}
	

});