package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginCheckerRefactored {

	private static final int MAXIMUM_LOCK_PERIOD_IN_MS = 60 * 60 * 1000;

	/**
	 * {@inheritDoc}.
	 */
	public Lock isUserAllowedToLogin(long id, String status, 
			boolean isFirstScreen, User userTryingToLogin,
			List existingLocks) {

		Object[] existingLock = (Object[]) existingLocks.get(0);
		String userIdWithLock = (String) existingLock[0];
		Date lockTimestamp = (Date) existingLock[1];


        //if no user holds the lock, create the write lock
		//for the current user trying to log in
		if (isNoUserHoldingCurrentLock(existingLocks,userIdWithLock)) {
			return createWriteLock();
		}
        //if the lock has not expired and current user is holding the lock,
		//then grant write lock
		if (!isLockExpired(lockTimestamp) &&
				currentUserHoldsCurrentLock(userIdWithLock, userTryingToLogin)) {
			return createWriteLock();
		}
		//if the lock has expired, 2 possible cases of granting the write lock
		//1. user is on the first screen or
		//2. current user is still holding the write lock
	    if (isLockExpired(lockTimestamp) &&
				(isFirstScreen || currentUserHoldsCurrentLock(userIdWithLock,userTryingToLogin))){
			   return createWriteLock();
		}

       /* if  (currentUserHoldsCurrentLock(userTryingToLogin, userIdWithLock)){
		    return createWriteLock();
		}

		long timeElapsedSinceLock = new Date().getTime() - lockTimestamp.getTime();
		if (isFirstScreen && timeElapsedSinceLock > MAXIMUM_LOCK_PERIOD_IN_MS) {
				return createWriteLock();
		}
		*/
		//if the lock has expired, current user is on the second screen and not holding the lock,
		//then grant this user a read lock
		//or, if the lock has not expired ,current user is not holding the lock, then grant
		//this user a read lock
		return createReadLockWithMessage(userIdWithLock);

	}

	private boolean isLockExpired (Date lockTimestamp){
		long timeElapsedSinceLock = new Date().getTime() - lockTimestamp.getTime();
		return timeElapsedSinceLock > MAXIMUM_LOCK_PERIOD_IN_MS;
	}

	private boolean isNoUserHoldingCurrentLock(List existingLocks, String userIdWithLock){
	    return (  (existingLocks.size() == 0 || existingLocks.get(0) == null)
		           || (userIdWithLock == null));
     }
    private boolean currentUserHoldsCurrentLock(String userIdWithLock, User userTryingToLogin){
		return userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId());
	 }


	private Lock createReadLockWithMessage(String userIdWithLock) {
		String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", userIdWithLock);
		Lock lock = new Lock();
		lock.setRead(true);
		// Only read access is permitted to other user
		lock.setLockReason(lockMsg);
		return lock;
	}

	private Lock createWriteLock() {
		Lock lock = new Lock();
		lock.setRead(false);
		return lock;
	}
}