/**
 *
 */
package jp.ac.shinshu.u.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.ac.shinshu.u.common.TransactionClosure;
import jp.ac.shinshu.u.common.TransactionManager;

/**
 * @author OsamuHASEGAWA
 *
 */
public class TransactionFilter implements Filter{

	public void destroy() {

	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		TransactionManager.executeTransaction(new TransactionClosure() {
			public void execute() throws Exception {
				chain.doFilter(request, response);
			}
		});
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
