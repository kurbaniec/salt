package pass.salt.code.annotations

/**
 * Marks a class as MongoRepo for MongoDB access.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MongoDB()