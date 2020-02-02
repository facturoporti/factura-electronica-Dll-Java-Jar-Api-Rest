package facturoporti.api.cfdi.genericos;

public final class StringHelper
{
	public static String substring(String string, int start, int length)
	{
		if (length < 0)
			throw new IndexOutOfBoundsException("Parameter length cannot be negative.");

		return string.substring(start, start + length);
	}

	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.length() == 0;
	}

	public static boolean isNullOrWhiteSpace(String string)
	{
		if (string == null)
			return true;

		for (int index = 0; index < string.length(); index++)
		{
			if ( ! Character.isWhitespace(string.charAt(index)))
				return false;
		}

		return true;
	}

	public static String join(String separator, String[] stringArray)
	{
		if (stringArray == null)
			return null;
		else
			return join(separator, stringArray, 0, stringArray.length);
	}

	public static String join(String separator, String[] stringArray, int startIndex, int count)
	{
		String result = "";

		if (stringArray == null)
			return null;

		for (int index = startIndex; index < stringArray.length && index - startIndex < count; index++)
		{
			if (separator != null && index > startIndex)
				result += separator;

			if (stringArray[index] != null)
				result += stringArray[index];
		}

		return result;
	}

	public static String remove(String string, int start)
	{
		return string.substring(0, start);
	}

	public static String remove(String string, int start, int count)
	{
		return string.substring(0, start) + string.substring(start + count);
	}

	public static String trimEnd(String string, Character... charsToTrim)
	{
		if (string == null || charsToTrim == null)
			return string;

		int lengthToKeep = string.length();
		for (int index = string.length() - 1; index >= 0; index--)
		{
			boolean removeChar = false;
			if (charsToTrim.length == 0)
			{
				if (Character.isWhitespace(string.charAt(index)))
				{
					lengthToKeep = index;
					removeChar = true;
				}
			}
			else
			{
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++)
				{
					if (string.charAt(index) == charsToTrim[trimCharIndex])
					{
						lengthToKeep = index;
						removeChar = true;
						break;
					}
				}
			}
			if ( ! removeChar)
				break;
		}
		return string.substring(0, lengthToKeep);
	}

	public static String trimStart(String string, Character... charsToTrim)
	{
		if (string == null || charsToTrim == null)
			return string;

		int startingIndex = 0;
		for (int index = 0; index < string.length(); index++)
		{
			boolean removeChar = false;
			if (charsToTrim.length == 0)
			{
				if (Character.isWhitespace(string.charAt(index)))
				{
					startingIndex = index + 1;
					removeChar = true;
				}
			}
			else
			{
				for (int trimCharIndex = 0; trimCharIndex < charsToTrim.length; trimCharIndex++)
				{
					if (string.charAt(index) == charsToTrim[trimCharIndex])
					{
						startingIndex = index + 1;
						removeChar = true;
						break;
					}
				}
			}
			if ( ! removeChar)
				break;
		}
		return string.substring(startingIndex);
	}


	public static String trim(String string, Character... charsToTrim)
	{
		return trimEnd(trimStart(string, charsToTrim), charsToTrim);
	}

	public static boolean stringsEqual(String s1, String s2)
	{
		if (s1 == null && s2 == null)
			return true;
		else
			return s1 != null && s1.equals(s2);
	}

	public static String padRight(String string, int totalWidth)
	{
		return padRight(string, totalWidth, ' ');
	}

	public static String padRight(String string, int totalWidth, char paddingChar)
	{
		StringBuilder sb = new StringBuilder(string);

		while (sb.length() < totalWidth)
		{
			sb.append(paddingChar);
		}

		return sb.toString();
	}

	public static String padLeft(String string, int totalWidth)
	{
		return padLeft(string, totalWidth, ' ');
	}

	public static String padLeft(String string, int totalWidth, char paddingChar)
	{
		StringBuilder sb = new StringBuilder("");

		while (sb.length() + string.length() < totalWidth)
		{
			sb.append(paddingChar);
		}

		sb.append(string);
		return sb.toString();
	}

	public static String repeatChar(char charToRepeat, int count)
	{
		String newString = "";
		for (int i = 1; i <= count; i++)
		{
			newString += charToRepeat;
		}
		return newString;
	}

	public static int lastIndexOf(String string, char value, int startIndex, int count)
	{
		int leftMost = startIndex + 1 - count;
		int rightMost = startIndex + 1;
		String substring = string.substring(leftMost, rightMost);
		int lastIndexInSubstring = substring.lastIndexOf(value);
		if (lastIndexInSubstring < 0)
			return -1;
		else
			return lastIndexInSubstring + leftMost;
	}

	public static int lastIndexOf(String string, String value, int startIndex, int count)
	{
		int leftMost = startIndex + 1 - count;
		int rightMost = startIndex + 1;
		String substring = string.substring(leftMost, rightMost);
		int lastIndexInSubstring = substring.lastIndexOf(value);
		if (lastIndexInSubstring < 0)
			return -1;
		else
			return lastIndexInSubstring + leftMost;
	}

	public static int indexOfAny(String string, char[] anyOf)
	{
		int lowestIndex = -1;
		for (char c : anyOf)
		{
			int index = string.indexOf(c);
			if (index > -1)
			{
				if (lowestIndex == -1 || index < lowestIndex)
				{
					lowestIndex = index;

					if (index == 0)
						break;
				}
			}
		}

		return lowestIndex;
	}

	public static int indexOfAny(String string, char[] anyOf, int startIndex)
	{
		int indexInSubstring = indexOfAny(string.substring(startIndex), anyOf);
		if (indexInSubstring == -1)
			return -1;
		else
			return indexInSubstring + startIndex;
	}

	public static int indexOfAny(String string, char[] anyOf, int startIndex, int count)
	{
		int endIndex = startIndex + count;
		int indexInSubstring = indexOfAny(string.substring(startIndex, endIndex), anyOf);
		if (indexInSubstring == -1)
			return -1;
		else
			return indexInSubstring + startIndex;
	}

	public static int lastIndexOfAny(String string, char[] anyOf)
	{
		int highestIndex = -1;
		for (char c : anyOf)
		{
			int index = string.lastIndexOf(c);
			if (index > highestIndex)
			{
				highestIndex = index;

				if (index == string.length() - 1)
					break;
			}
		}

		return highestIndex;
	}

	public static int lastIndexOfAny(String string, char[] anyOf, int startIndex)
	{
		String substring = string.substring(0, startIndex + 1);
		int lastIndexInSubstring = lastIndexOfAny(substring, anyOf);
		if (lastIndexInSubstring < 0)
			return -1;
		else
			return lastIndexInSubstring;
	}

	public static int lastIndexOfAny(String string, char[] anyOf, int startIndex, int count)
	{
		int leftMost = startIndex + 1 - count;
		int rightMost = startIndex + 1;
		String substring = string.substring(leftMost, rightMost);
		int lastIndexInSubstring = lastIndexOfAny(substring, anyOf);
		if (lastIndexInSubstring < 0)
			return -1;
		else
			return lastIndexInSubstring + leftMost;
	}

}