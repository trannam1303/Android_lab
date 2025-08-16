import java.math.BigInteger
import java.security.SecureRandom

object DiffieHellman {

    private val random = SecureRandom()

    fun privateKey(prime: BigInteger): BigInteger {
        return BigInteger(prime.bitLength(), random).mod(prime - BigInteger.ONE) + BigInteger.ONE
    }

    fun publicKey(p: BigInteger, g: BigInteger, privKey: BigInteger): BigInteger {
        return g.modPow(privKey, p)
    }

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger {
        return publicKey.modPow(privateKey, prime)
    }
}
