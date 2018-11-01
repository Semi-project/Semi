package service.comment.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.free.Free_CommentDao;
import dao.comment.free.Free_CommentDaoImpl;
import dto.board.Free_Board;
import dto.comment.Free_Comments;

public class Free_CommentServiceImpl implements Free_CommentService {

		Free_CommentDao freecommentdao = new Free_CommentDaoImpl();
	@Override
	public Free_Comments view(Free_Comments free_Comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Free_Comments getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}


	

	@Override
	public void updateFree_Comments(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWriter(HttpServletRequest req, Free_Comments free_Comments) {
		// TODO Auto-generated method stub
		return false;
	}


	

	@Override
	public void insertComment(Free_Comments comment) {
		freecommentdao.insert(comment);
	}
	@Override
	public List getCommentList(Free_Board freeboard) {
		
		return freecommentdao.selectComment(freeboard);
	}

	@Override
	public boolean deleteComment(Free_Comments comment) {
	freecommentdao.deleteComment(comment); 
		
		if( freecommentdao.countComment(comment) > 0 ) {
			return false;
		} else {
			return true;
		}
	}

	
}
