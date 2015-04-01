/**
 *
 */
package jp.ac.shinshu.u.common;

/**
 * @since 2010/06/22
 * @author OsamuHASEGAWA
 *
 */
public interface TransactionClosure {

	/**
	 * トランザクションとして実行する処理
	 * @throws Exception
	 */
	void execute() throws Exception;

}
