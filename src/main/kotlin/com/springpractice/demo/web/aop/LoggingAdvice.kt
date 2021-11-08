package com.springpractice.demo.web.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

private val logger = LoggerFactory.getLogger(LoggingAdvice::class.java)

@Aspect
@Component
class LoggingAdvice {
    // @Before("execution(* com.springpractice.demo.web.*.*(..))")
    // fun beforeLog(joinPoint: JoinPoint) {
    //     val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
    //     logger.info("Start: ${joinPoint.signature} userId=${user.id}")
    //     logger.info("Class: ${joinPoint.target.javaClass}")
    //     logger.info("Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}")
    // }
    //
    // @After("execution(* com.springpractice.demo.web.*.*(..))")
    // fun afterLog(joinPoint: JoinPoint) {
    //     val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
    //     logger.info("End: ${joinPoint.signature} userId=${user.id}")
    // }

    // @Around("execution(* com.springpractice.demo.web.*.*(..))")
    // fun aroundLog(joinPoint: ProceedingJoinPoint): Any? {
    //     // 前
    //     val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
    //     logger.info("Start Proceed: ${joinPoint.signature} userId=${user.id}")
    //
    //     // 本処理
    //     val result = joinPoint.proceed()
    //
    //     // 後
    //     logger.info("End Proceed: ${joinPoint.signature} userId=${user.id}")
    //
    //     // 本処理の結果の返却
    //     return result
    // }

    @AfterReturning("execution(* com.springpractice.demo.web.*.*(..))", returning = "returnValue")
    fun afterReturningLog(joinPoint: JoinPoint, returnValue: Any?) {
        logger.info("End Proceed: ${joinPoint.signature} returnValue=$returnValue")
    }

    @AfterThrowing("execution(* com.springpractice.demo.web.*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: Throwable) {
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
    }
}
