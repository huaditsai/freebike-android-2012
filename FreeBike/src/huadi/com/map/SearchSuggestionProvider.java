package huadi.com.map;

import android.content.SearchRecentSuggestionsProvider;

public class SearchSuggestionProvider extends SearchRecentSuggestionsProvider //·j´Mªº¾ú¥v¬ö¿ý
{
	public final static String AUTHORITY="huadi.com.map.SearchSuggestionProvider";
	public final static int MODE=DATABASE_MODE_QUERIES;
	
	public SearchSuggestionProvider()
	{
		super();
		setupSuggestions(AUTHORITY, MODE);
	}
}
