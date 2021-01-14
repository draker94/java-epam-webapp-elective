<%@tag language="java" pageEncoding="UTF-8" %>

<form>
    <label>
        <select name="language" onchange="submit()">
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        </select>
    </label>
</form>