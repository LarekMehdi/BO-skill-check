package fr.perso.skillcheck.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.perso.skillcheck.question.Question;
import fr.perso.skillcheck.security.UserPrincipal;

public abstract class UtilEntity {

    /** EMPTY **/
    
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;

        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;
    }

    /** USER **/

    public static boolean isMyId(Long id, UserPrincipal user) {
        if (Objects.equals(id, user.getId())) return true;
        return false;
    }

    /** SUCCESS RATE **/

    public static double computeSuccessRate(List<Question> questionList) {
        if (questionList == null || questionList.isEmpty()) return 0.0;
        return questionList.stream().mapToDouble(Question::getSuccessRate).average().orElse(0.0);
    }

    /** TIME LIMIT **/

    public static int computeTimeLimit(List<Question> questionList) {
        if (questionList == null || questionList.isEmpty()) return 0;
        return questionList.stream().mapToInt(Question::getTimeLimit).sum();
    }
}
