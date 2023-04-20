package com.zshop.helper.utils.enums;

public enum VmFileEnum {

	SMS("vm_seller_forget_otp.vm"), EMAIL("vm_seller_forget_otp.vm");

	private String fileName;

	VmFileEnum(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public enum Shows {

		CANCELLED("vm_booking_cancel.vm"), RESCHEDULE_BOOKING("vm_booking_reschedule.vm"),
		BOOKING_APPROVED("vm_booking_approved.vm"), BOOKING_REJECTED("vm_booking_rejected.vm"),
		BOOKING_CONFIRMED("vm_booking_approved.vm"), BOOKING_REVIEW("vm_booking_review.vm"), AIRED("vm_show_aired");

		private String source;

		Shows(String source) {
			this.source = source;
		}

		public String getSource() {

			return source;
		}
	}

	public enum SellerOnboarding {

		PASSWORD_CHANGE_NOTIFY("password_change_notify.vm");

		private String source;

		SellerOnboarding(String source) {
			this.source = source;
		}

		public String getSource() {

			return source;
		}
	}

}
