package bracu.ac.bd.ocr.language;

import bracu.ac.bd.ocr.R;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

/**
 * Class for handling functions relating to converting between standard language
 * codes, and converting language codes to language names.
 */
public class LanguageCodeHelper {
	public static final String TAG = "LanguageCodeHelper";

	/**
	 * Private constructor to enforce noninstantiability
	 */
	private LanguageCodeHelper() {
		throw new AssertionError();
	}

	/**
	 * Map an ISO 639-3 language code to an ISO 639-1 language code.
	 * 
	 * There is one entry here for each language recognized by the OCR engine.
	 * 
	 * @param languageCode
	 *            ISO 639-3 language code
	 * @return ISO 639-1 language code
	 */
	public static String mapLanguageCode(String languageCode) {
		if (languageCode.equals("ben")) { // Bengali
			return "bn";
		} else if (languageCode.equals("eng")) { // English
			return "en";
		} else {
			return "";
		}
	}

	/**
	 * Map the given ISO 639-3 language code to a name of a language, for
	 * example, "Spanish"
	 * 
	 * @param context
	 *            interface to calling application environment. Needed to access
	 *            values from strings.xml.
	 * @param languageCode
	 *            ISO 639-3 language code
	 * @return language name
	 */
	public static String getOcrLanguageName(Context context, String languageCode) {
		Resources res = context.getResources();
		String[] language6393 = res.getStringArray(R.array.iso6393);
		String[] languageNames = res.getStringArray(R.array.languagenames);
		int len;

		// Finds the given language code in the iso6393 array, and takes the
		// name with the same index
		// from the languagenames array.
		for (len = 0; len < language6393.length; len++) {
			if (language6393[len].equals(languageCode)) {
				Log.d(TAG, "getOcrLanguageName: " + languageCode + "->"
						+ languageNames[len]);
				return languageNames[len];
			}
		}

		Log.d(TAG, "languageCode: Could not find language name for ISO 693-3: "
				+ languageCode);
		return languageCode;
	}

	/**
	 * Map the given ISO 639-1 language code to a name of a language, for
	 * example, "Spanish"
	 * 
	 * @param languageCode
	 *            ISO 639-1 language code
	 * @return name of the language. For example, "English"
	 */
	public static String getTranslationLanguageName(Context context,
			String languageCode) {
		Resources res = context.getResources();
		String[] language6391 = res
				.getStringArray(R.array.translationtargetiso6391_google);
		String[] languageNames = res
				.getStringArray(R.array.translationtargetlanguagenames_google);
		int len;

		// Finds the given language code in the translationtargetiso6391 array,
		// and takes the name
		// with the same index from the translationtargetlanguagenames array.
		for (len = 0; len < language6391.length; len++) {
			if (language6391[len].equals(languageCode)) {
				Log.d(TAG, "getTranslationLanguageName: " + languageCode + "->"
						+ languageNames[len]);
				return languageNames[len];
			}
		}

		// Now look in the Microsoft Translate API list. Currently this will
		// only be needed for
		// Haitian Creole.
		language6391 = res
				.getStringArray(R.array.translationtargetiso6391_microsoft);
		languageNames = res
				.getStringArray(R.array.translationtargetlanguagenames_microsoft);
		for (len = 0; len < language6391.length; len++) {
			if (language6391[len].equals(languageCode)) {
				Log.d(TAG, "languageCode: " + languageCode + "->"
						+ languageNames[len]);
				return languageNames[len];
			}
		}

		Log.d(TAG,
				"getTranslationLanguageName: Could not find language name for ISO 693-1: "
						+ languageCode);
		return "";
	}

}
